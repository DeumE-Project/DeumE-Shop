<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
    <div class="uploadDiv">
        <input type="file" name="uploadFile" multiple />
    </div>
    <button id="uploadBtn">Upload Multiple Files</button>

    <script>
        $(document).ready(function() {
           $("#uploadBtn").on("click", function(e) {
               var formData = new FormData();
               var inputFile = $("input[name='uploadFile']");
               var files = inputFile[0].files;
               console.log(files);

               for (var i = 0; i < files.length; i++) {
                   formData.append("uploadFile", files[i]);
               }

               $.ajax({
                   url: "${pageContext.request.contextPath}/customer/uploadAjaxAction",
                   processData: false,
                   contentType: false,
                   data: formData,
                   type: "POST",
                   success: function(result) {
                       alert("upload success");
                   },
                   error: function(result) {
                       alert("error");
                   }
               })
           });
        });
    </script>
</body>
</html>
