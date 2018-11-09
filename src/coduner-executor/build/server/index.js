"use strict";

var _express = _interopRequireDefault(require("express"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var _require = require('vm2'),
    VM = _require.VM;

var app = (0, _express.default)();
var vm = new VM();
app.post('/', function (req, res) {
  res.send(200, vm.run('function a() {return 4;}; setTimeout(2000, a);'));
});
app.listen(3000);