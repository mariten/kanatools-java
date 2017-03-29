var sha1 = require('sha1');


// API Endpoints
var API_GATEWAY         = 'https://xyxx8286p6.execute-api.us-east-1.amazonaws.com/prod/kana'
var CONVERT_ENDPOINT    = '/convert?input_str={STR}&conv_ops={OPS}';


// Data Holders
var _conversions = {}


// Module code
var KanaToolsModel = {
    performConversion: function(input_str, conv_ops) {
        var key = sha1(input_str + conv_ops);

        // Perform Ajax request
        var conversion_request = API_GATEWAY + CONVERT_ENDPOINT.replace('{STR}', input_str).replace('{OPS}', conv_ops)
        $.ajax({
            'url':      conversion_request,
            'type':     'GET',
            'dataType': 'json',
            'success': function(response_json) {
                _conversions[key] = response_json
                KanaToolsModel.notifyChange();
            },
            'error': function(xhr, http_status) {
                _conversions[key] = {'http_status': http_status}
                KanaToolsModel.notifyChange();
            },
        });
    },


    notifyChange: function() {
        console.log('done')
    },
};

module.exports = KanaToolsModel;
