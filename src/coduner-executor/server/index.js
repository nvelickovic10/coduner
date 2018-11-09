import express from 'express';
const { VM } = require('vm2');

const app = express();
const vm = new VM();

app.post('/', (req, res) => {
    res.send(200, vm.run('function a() {return 4;}; setTimeout(2000, a);'));
});

app.listen(3000);