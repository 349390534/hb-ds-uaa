/**
 * 
 */
function HashMap() {
	var map = function(key, value) {
		this.key = key;
		this.value = value;
	};

	var put = function(key, value) {
		for (var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				this.arr[i].value = value;
				return;
			}
		}
		this.arr[this.arr.length] = new map(key, value);
	};

	var get = function(key) {
		for (var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				return this.arr[i].value;
			}
		}
		return null;
	};

	var remove = function(key) {
		var v;
		for (var i = 0; i < this.arr.length; i++) {
			v = this.arr.pop();
			if (v.key === key) {
				continue;
			}
			this.arr.unshift(v);
		}
	};

	var size = function() {
		return this.arr.length;
	};

	var isEmpty = function() {
		return this.arr.length == 0;
	};
	
	var keySets = function (){
		var  keySet = new Array();
		for (var i = 0; i < this.arr.length; i++) {
			keySet[i] = this.arr[i].key;
		}
		return keySet;
	};
	var valueSets = function (){
		var  valueSet = new Array();
		for (var i = 0; i < this.arr.length; i++) {
			valueSet[i] = this.arr[i].value;
		}
		return valueSet;
	};
	
	this.arr = new Array();
	this.map = map;
	this.get = get;
	this.put = put;
	this.remove = remove;
	this.size = size;
	this.isEmpty = isEmpty;
	this.keySets = keySets;
	this.valueSets = valueSets;
}
