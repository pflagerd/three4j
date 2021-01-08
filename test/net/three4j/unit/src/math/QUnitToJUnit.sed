s#^.*QUnit\.test.+"([a-zA-Z0-9_$]+)".+$#	@Test\n	public void \1() {#
#s#assert.strictEqual\( ([^,]+), ([^,]+), (.+)\)#assertEquals\( \2, \1, \3 \)#
s#assert.strictEqual#assertEquals#
s#} \);#}#
