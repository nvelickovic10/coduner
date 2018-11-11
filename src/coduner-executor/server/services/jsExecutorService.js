import { VM, VMScript } from 'vm2';

const constants = {
    NS_PER_SEC: 1e9
};

const _getTimeNanos = (time = undefined) => {
    const diff = process.hrtime(time);
    return diff[0] * constants.NS_PER_SEC + diff[1];
};

const _compile = codeString => {
    try {
        return new VMScript(codeString).compile();
    } catch (err) {
        // console.error('_compile:', err);
        throw {
            message: 'Failed to compile code: ' + err.message,
            code: JSON.stringify(1),
            stack: err.stack
        };
    }
};

const _run = script => {
    try {
        const vm = new VM({
            timeout: 1000,
            sandbox: {
                // setTimeout: setTimeout
            } //global VM object
        });
        return vm.run(script);
    } catch (err) {
        // console.error('_run:', err);
        throw {
            message: 'Failed to run code: ' + err.message,
            code: JSON.stringify(2),
            stack: err.stack
        };
    }
};

const execute = codeString => {
    const startTime = _getTimeNanos();
    let compileTime;
    let runTime;
    try {
        const script = _compile(codeString);
        compileTime = _getTimeNanos();
        const result = _run(script);
        runTime = _getTimeNanos();
        return {
            result: JSON.stringify(result),
            startTime: '' + startTime,
            compileTime: '' + compileTime,
            runTime: '' + runTime,
            endTime: '' + _getTimeNanos()
        };
    } catch (err) {
        runTime = compileTime ? runTime || _getTimeNanos() : runTime || -1;
        compileTime = compileTime || _getTimeNanos();
        throw {
            message: err.message,
            code: err.code,
            stack: err.stack,
            startTime: '' + startTime,
            compileTime: '' + compileTime,
            runTime: '' + runTime,
            endTime: '' + _getTimeNanos()
        };
    }
};

export default {
    execute
};
