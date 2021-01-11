s#assert.ok#assertTrue#
#s#([a-zA-Z$_][a-zA-Z0-9$_]+)\.([xyz])#\1.\2\(\)#
s#(.*)var ([a-zA-Z$_][a-zA-Z0-9$_]*) = new ([a-zA-Z$_][a-zA-Z0-9$_]*)\((.*)\).*;#\1\3 \2 = new \3\(\4\);#
s#//(.+}) \);#\1#
s#^.*QUnit\.test.*"(.*[a-zA-Z0-9_$]+)".+$#	@Test\n	public void \1() {#
s#QUnit\.todo.*"([a-zA-Z0-9_$]+)".+#	@Test\n	public void \1() {#
s#assert.strictEqual#assertEquals#
s#} \);#}#
s#===#==#
s#!==#!=#
s#'([^']+)'#"\1"#