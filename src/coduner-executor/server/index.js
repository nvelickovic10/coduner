import bodyParser from 'body-parser';
import express from 'express';
import jsExecutorService from './services/jsExecutorService';

const app = express();

app.use(bodyParser.json());

app.post('/', (req, res) => {
    try {
        const result = jsExecutorService.execute(req.body.codeString);
        res.status(200).send(result);
    } catch (err) {
        res.status(500).send(err);
    }
});

app.listen(3000, () => {
    console.log('Listening on localhost:3000');
});
