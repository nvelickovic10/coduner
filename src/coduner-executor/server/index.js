import express from 'express';
import bodyParser from 'body-parser';
import { VM } from 'vm2';

const app = express();
const vm = new VM();

app.use(bodyParser.json());

app.post('/', (req, res) => {
    console.log(req.body);
    const response = {
        result: vm.run(req.body.codeString)
    };
    res.status(200).send(response);
});

app.listen(3000, () => {
    console.log("Listening on localhost:3000");
});