var exec = require('cordova/exec');

var contactRingtone = {
  setRingtone: function(contactId, ringtonePath, successCallback, errorCallback) {
    function onError(error) {
      errorCallback(error);
    }

    function onSuccess(msg) {
      successCallback(msg);
    }
    return exec(onSuccess, onError, "ContactRingtone", 'setRingtone', [contactId, ringtonePath]);
  }
}

module.exports = contactRingtone;
