import express from 'express';
const { VM } = require('vm2');
import fs from 'fs';

import arrayUtil from './util/arrayUtil';

const app = express();
const vm = new VM();

const publicDir = './public';
const galleryDir = `${publicDir}/resources/gallery/`;

app.use(express.static(publicDir));

app.get('/', (req, res) => {
    res.sendfile(`${publicDir}/index.html`);
});

app.get('/images', (req, res) => {
    fs.readdir(galleryDir, (err, files) => {
        for (let i = 0; i < files.length; i++) {
            files[i] = `resources/gallery/${files[i]}`;
        }
        res.send(arrayUtil.shuffle(files));
    })
});

app.post('/execjs', (req, res) => {
    res.send(200, vm.run('function a() {return 4;}; setTimeout(2000, a);'));
});

app.listen(3000);