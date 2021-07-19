var mysql = require('mysql');

var con = mysql.createConnection({
    host: "localhost",
    user: "PatriciaFiona",
    password: "12345",
    database: "underarmour"
});

con.connect(function (err){
    if(err) throw err;
});

module.exports = con;