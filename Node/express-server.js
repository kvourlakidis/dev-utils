// dependencies
const https = require('https')
const forceSsl = require('express-force-ssl')
const express = require('express') // server framework
const path = require('path')       // file and directory paths utility
const fs = require('fs')           // file system module
const port = 443
// encryption
const key = fs.readFileSync('encryption/private.key')
const cert = fs.readFileSync('encryption/i2host.crt')

const options = {
    key: key,
    cert: cert,
    ca: cert
}

const app = express().use(forceSsl)

app.get('/', function(req, res){
    res.send('Got a GET request for /')
})

app.post('/', function(req, res){
    res.send('Got a POST request')
})

app.put('/user', function(req, res){
    res.send('Got a PUT request at /user')
})

app.delete('/user', function(req, res){
    res.send('Got a DELETE request at /user')
})

app.use('/static', express.static(path.join(__dirname, 'public')))

// app.listen(3001, () => console.log('Example app listening on port 3000!'))
https.createServer(options, app).listen(3001, () => {
    console.log('Example app listening on port 3000!')
})