var http = require('http');

var handleRequest = function(request, response) {
  console.log('Received request for URL: ' + request.url);
  response.writeHead(200);
  response.end('Hello World!');
};
http.createServer(handleRequest).listen(8080, function(err){
  if (err) {
    console.error(err);
    return;
  }
  console.log("Server listening on port 8080");
});
