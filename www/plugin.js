var exec = require('cordova/exec');

var PLUGIN_NAME = 'ContactRingtone';

var contactRingtone = {
  setRingtone: function(contactId, phoneNumber, ringtonePath, success, error) {
    exec(success, error, PLUGIN_NAME, 'set_ringtone', [contactId, ringtonePath]);
  }
};

module.exports = contactRingtone;
