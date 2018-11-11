import { VM, VMScript } from 'vm2';

const constants = {
    NS_PER_SEC: 1e9,
    SUCCESS: 'success',
    CODE_SUCCESS: 0,
    CODE_COMPILE_ERROR: 1,
    CODE_RUN_ERROR: 2
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
            message: err.message,
            stack: err.message,
            code: constants.CODE_COMPILE_ERROR
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
            message: err.message,
            stack: err.message,
            code: constants.CODE_RUN_ERROR
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
            message: constants.SUCCESS,
            code: constants.CODE_SUCCESS,
            startTime: startTime,
            compileTime: compileTime,
            runTime: runTime,
            endTime: _getTimeNanos(),
            result: result
        };
    } catch (err) {
        runTime = compileTime ? runTime || _getTimeNanos() : runTime || -1;
        compileTime = compileTime || _getTimeNanos();
        throw {
            message: err.message,
            startTime: startTime,
            compileTime: compileTime,
            runTime: runTime,
            endTime: _getTimeNanos(),
            result: err.stack,
        };
    }
};

export default {
    execute
};
