const http = require('http')
const hostname = '127.0.0.1'
const port = 3001

function myHandler(req,res) {
	res.statusCode = 200;
	res.setHeader('Content-Type', 'text/plain')
	res.end('Hello World\n')

	console.log('\n\n')
	console.log('Method: ' + req.method)
	console.log('URL: ' + req.url)
	console.log('Referer:  ' + req.headers.referer)
	console.log('Origin: ' + req.headers.origin)
}

function startHandler() {
	let addr = `http://${hostname}:${port}`
	console.log('Server running at ' + addr)
}

const myServer = http.createServer(myHandler)
myServer.listen(port, hostname, startHandler)
