const http = require('http')
const hostname = '127.0.0.1'
const port = 3001

const myServer = http.createServer(myHandler)

function myHandler(req,res){
	res.statusCode = 200;
	res.setHeader('Content-Type', 'text/plain')
	res.end('Hello World\n')

	console.log('\n\n')
	console.log('Method: ' + req.method)
	console.log('URL: ' + req.url)
	console.log('Referer:  ' + req.headers.referer)
	console.log('Origin: ' + req.headers.origin)
}


myServer.listen(port,hostname, function(){
	console.log(`Server running at http://${hostname}:${port}`)
})
