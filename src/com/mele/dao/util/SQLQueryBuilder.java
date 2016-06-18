/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mele.dao.util;

import java.util.regex.Pattern;

import com.mele.util.TextUtils;

/**
 * This is a convience class that helps build SQL queries to be sent to
 * {@link SQLiteDatabase} objects.
 */
public class SQLQueryBuilder {
	private static final String TAG = SQLQueryBuilder.class.getSimpleName();
	private static final Pattern sLimitPattern = Pattern
			.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");

	private String mTables = "";
	private StringBuilder mWhereClause = null; // lazily created
	private boolean mDistinct;

	public SQLQueryBuilder() {
		mDistinct = false;
	}

	/**
	 * Mark the query as DISTINCT.
	 *
	 * @param distinct
	 *            if true the query is DISTINCT, otherwise it isn't
	 */
	public void setDistinct(boolean distinct) {
		mDistinct = distinct;
	}

	/**
	 * Returns the list of tables being queried
	 *
	 * @return the list of tables being queried
	 */
	public String getTables() {
		return mTables;
	}

	/**
	 * Sets the list of tables to query. Multiple tables can be specified to
	 * perform a join. For example: setTables("foo, bar")
	 * setTables("foo LEFT OUTER JOIN bar ON (foo.id = bar.foo_id)")
	 *
	 * @param inTables
	 *            the list of tables to query on
	 */
	public void setTables(String inTables) {
		mTables = inTables;
	}

	/**
	 * Append a chunk to the WHERE clause of the query. All chunks appended are
	 * surrounded by parenthesis and ANDed with the selection passed to
	 * {@link #query}. The final WHERE clause looks like:
	 *
	 * WHERE (&lt;append chunk 1>&lt;append chunk2>) AND (&lt;query() selection
	 * parameter>)
	 *
	 * @param inWhere
	 *            the chunk of text to append to the WHERE clause.
	 */
	public void appendWhere(CharSequence inWhere) {
		if (mWhereClause == null) {
			mWhereClause = new StringBuilder(inWhere.length() + 16);
		}
		if (mWhereClause.length() == 0) {
			mWhereClause.append('(');
		}
		mWhereClause.append(inWhere);
	}

	/**
	 * Append a chunk to the WHERE clause of the query. All chunks appended are
	 * surrounded by parenthesis and ANDed with the selection passed to
	 * {@link #query}. The final WHERE clause looks like:
	 *
	 * WHERE (&lt;append chunk 1>&lt;append chunk2>) AND (&lt;query() selection
	 * parameter>)
	 *
	 * @param inWhere
	 *            the chunk of text to append to the WHERE clause. it will be
	 *            escaped to avoid SQL injection attacks
	 */
	public void appendWhereEscapeString(String inWhere) {
		if (mWhereClause == null) {
			mWhereClause = new StringBuilder(inWhere.length() + 16);
		}
		if (mWhereClause.length() == 0) {
			mWhereClause.append('(');
		}
	}

	/**
	 * Build an SQL query string from the given clauses.
	 *
	 * @param distinct
	 *            true if you want each row to be unique, false otherwise.
	 * @param tables
	 *            The table names to compile the query against.
	 * @param columns
	 *            A list of which columns to return. Passing null will return
	 *            all columns, which is discouraged to prevent reading data from
	 *            storage that isn't going to be used.
	 * @param where
	 *            A filter declaring which rows to return, formatted as an SQL
	 *            WHERE clause (excluding the WHERE itself). Passing null will
	 *            return all rows for the given URL.
	 * @param groupBy
	 *            A filter declaring how to group rows, formatted as an SQL
	 *            GROUP BY clause (excluding the GROUP BY itself). Passing null
	 *            will cause the rows to not be grouped.
	 * @param having
	 *            A filter declare which row groups to include in the cursor, if
	 *            row grouping is being used, formatted as an SQL HAVING clause
	 *            (excluding the HAVING itself). Passing null will cause all row
	 *            groups to be included, and is required when row grouping is
	 *            not being used.
	 * @param orderBy
	 *            How to order the rows, formatted as an SQL ORDER BY clause
	 *            (excluding the ORDER BY itself). Passing null will use the
	 *            default sort order, which may be unordered.
	 * @param limit
	 *            Limits the number of rows returned by the query, formatted as
	 *            LIMIT clause. Passing null denotes no LIMIT clause.
	 * @return the SQL query string
	 */
	public static String buildQueryString(boolean distinct, String tables,
			String[] columns, String where, String groupBy, String having,
			String orderBy, String limit) {
		if (TextUtils.isEmpty(groupBy) && !TextUtils.isEmpty(having)) {
			throw new IllegalArgumentException(
					"HAVING clauses are only permitted when using a groupBy clause");
		}
		if (!TextUtils.isEmpty(limit)
				&& !sLimitPattern.matcher(limit).matches()) {
			throw new IllegalArgumentException("invalid LIMIT clauses:" + limit);
		}

		StringBuilder query = new StringBuilder(120);

		query.append("SELECT ");
		if (distinct) {
			query.append("DISTINCT ");
		}
		if (columns != null && columns.length != 0) {
			appendColumns(query, columns);
		} else {
			query.append("* ");
		}
		query.append("FROM ");
		query.append(tables);
		appendClause(query, " WHERE ", where);
		appendClause(query, " GROUP BY ", groupBy);
		appendClause(query, " HAVING ", having);
		appendClause(query, " ORDER BY ", orderBy);
		appendClause(query, " LIMIT ", limit);

		return query.toString();
	}

	private static void appendClause(StringBuilder s, String name, String clause) {
		if (!TextUtils.isEmpty(clause)) {
			s.append(name);
			s.append(clause);
		}
	}

	/**
	 * Add the names that are non-null in columns to s, separating them with
	 * commas.
	 */
	public static void appendColumns(StringBuilder s, String[] columns) {
		int n = columns.length;

		for (int i = 0; i < n; i++) {
			String column = columns[i];

			if (column != null) {
				if (i > 0) {
					s.append(", ");
				}
				s.append(column);
			}
		}
		s.append(' ');
	}

	/**
	 * Given a set of subqueries, all of which are SELECT statements, construct
	 * a query that returns the union of what those subqueries return.
	 * 
	 * @param subQueries
	 *            an array of SQL SELECT statements, all of which must have the
	 *            same columns as the same positions in their results
	 * @param sortOrder
	 *            How to order the rows, formatted as an SQL ORDER BY clause
	 *            (excluding the ORDER BY itself). Passing null will use the
	 *            default sort order, which may be unordered.
	 * @param limit
	 *            The limit clause, which applies to the entire union result set
	 *
	 * @return the resulting SQL SELECT statement
	 */
	public String buildUnionQuery(String[] subQueries, String sortOrder,
			String limit) {
		StringBuilder query = new StringBuilder(128);
		int subQueryCount = subQueries.length;
		String unionOperator = mDistinct ? " UNION " : " UNION ALL ";

		for (int i = 0; i < subQueryCount; i++) {
			if (i > 0) {
				query.append(unionOperator);
			}
			query.append(subQueries[i]);
		}
		appendClause(query, " ORDER BY ", sortOrder);
		appendClause(query, " LIMIT ", limit);
		return query.toString();
	}

}
