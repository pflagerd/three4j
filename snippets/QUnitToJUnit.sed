s#assert.ok#assertTrue#
s#assertEquals[[:space:]]*\([[:space:]]*([^,]+),[[:space:]]*([^,]+),(.*)$#assertEquals\(\2, \1, \3#
#s#([a-zA-Z$_][a-zA-Z0-9$_]+)\.([xyz])#\1.\2\(\)#
s#(.*)var ([a-zA-Z$_][a-zA-Z0-9$_]*) = new ([a-zA-Z$_][a-zA-Z0-9$_]*)\((.*)\).*;#\1\3 \2 = new \3\(\4\);#
s#//(.+}) \);#\1#
s#^.*QUnit\.test.*"(.*[a-zA-Z0-9_$]+)".+$#	@Test\n	public void \1() {#
s#QUnit\.todo.*"([a-zA-Z0-9_$]+)".+#	@Test\n	public void \1() {#
s#} \);#}#
s#===#==#g
s#!==#!=#g
s#'([^']+)'#"\1"#
s#\[([^]]{4,32})\]#new double\[\] {\1}#
