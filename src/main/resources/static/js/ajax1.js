// ajax1.js
function retrieveCryptoResults() {
	
	var url = '/cryptoRateResults?fiat='.concat(fiatOptionValue).concat('&crypto=')
		.concat(cryptoOptionValue);
	$("#resultsBlock").load(url);
}
