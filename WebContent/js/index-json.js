function getImage() {
   $.getJSON('js/e-json.js', function(data) {
       		 $.each(data.slideData, function(i, item) {
         		 	 $("#slideAdd").append(
         		 	 	"<li>"
         		 	 	+"<a href="
         		 	 	+item.href
         		 	 	+">"
         		 	     +"<img src="
         		 	 	+item.url
         		 	 	+" alt=''>"                          
         		 	 	+"</a>"
         		 	 	+"</li>"
         		 	 	);
       		 }); 
   });
}
getImage();