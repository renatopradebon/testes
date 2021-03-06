'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _stringify;

function _load_stringify() {
  return _stringify = _interopRequireDefault(require('babel-runtime/core-js/json/stringify'));
}

var _asyncToGenerator2;

function _load_asyncToGenerator() {
  return _asyncToGenerator2 = _interopRequireDefault(require('babel-runtime/helpers/asyncToGenerator'));
}

var _promise;

function _load_promise() {
  return _promise = _interopRequireDefault(require('babel-runtime/core-js/promise'));
}

var _constants;

function _load_constants() {
  return _constants = _interopRequireWildcard(require('../constants.js'));
}

var _fs;

function _load_fs() {
  return _fs = _interopRequireWildcard(require('../util/fs.js'));
}

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } else { var newObj = {}; if (obj != null) { for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key]; } } newObj.default = obj; return newObj; } }

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/* eslint no-unused-vars: 0 */

const path = require('path');

class BaseFetcher {
  constructor(dest, remote, config) {
    this.reporter = config.reporter;
    this.reference = remote.reference;
    this.registry = remote.registry;
    this.hash = remote.hash;
    this.remote = remote;
    this.config = config;
    this.dest = dest;
  }

  getResolvedFromCached(hash) {
    // fetcher subclasses may use this to perform actions such as copying over a cached tarball to the offline
    // mirror etc
    return (_promise || _load_promise()).default.resolve();
  }

  _fetch() {
    return (_promise || _load_promise()).default.reject(new Error('Not implemented'));
  }

  fetch() {
    var _this = this;

    const dest = this.dest;


    return (_fs || _load_fs()).lockQueue.push(dest, (0, (_asyncToGenerator2 || _load_asyncToGenerator()).default)(function* () {
      yield (_fs || _load_fs()).mkdirp(dest);

      // fetch package and get the hash

      var _ref2 = yield _this._fetch();

      const hash = _ref2.hash;
      const resolved = _ref2.resolved;

      // load the new normalized manifest

      const pkg = yield _this.config.readManifest(dest, _this.registry);

      yield (_fs || _load_fs()).writeFile(path.join(dest, (_constants || _load_constants()).METADATA_FILENAME), (0, (_stringify || _load_stringify()).default)({
        artifacts: [],
        remote: _this.remote,
        registry: _this.registry,
        hash: hash
      }, null, '  '));

      return {
        resolved: resolved,
        hash: hash,
        dest: dest,
        package: pkg,
        cached: false
      };
    }));
  }
}
exports.default = BaseFetcher;