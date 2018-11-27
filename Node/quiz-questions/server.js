var http = require('http'),
    express = require('express'),
    port = normalizePort(process.env.PORT || '3000'),
    // hostname = 'localhost',
    hostname = '9.175.119.211'
    rootDir = __dirname + '/public'

var app = express()

app.get("/", function(req, res) {
    res.sendFile('quiz_questions.html' , { root : rootDir })
});

app.use('/', express.static(rootDir))
 
var server = http.createServer( app )

server.listen(port, hostname)
server.on( 'error', onError )
server.on( 'listening', onListening )

function onListening(){
    console.log(`Server listening on http://${server.address().address}:${server.address().port}`)
    // console.log(`Server listening on port ${port}`)
}

function onError ( error ) {
    if (error.syscall !== 'listen') {
        throw error;
    }
    switch (error.code) {
        case 'EADDRINUSE':
            console.error(port + ' is already in use, quitting...' )
            process.exit(1)
            break
        case 'EADDRNOTAVAIL':
            console.error(hostname + ' is not available, quitting...')
            process.exit(1)
            break
        case 'EACCES':
            console.error('could not start server on: ' + 
                hostname + ':' + port + ', quitting...' )
            process.exit(1)
            break
        default:
            throw error
    }
}

function normalizePort( val ) {
    var port = parseInt(val, 10)

    if ( isNaN(port) ) {
        return val
    }
    if (port >= 0) {
        return port
    }
    return false
}