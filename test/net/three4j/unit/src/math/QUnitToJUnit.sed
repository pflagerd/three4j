#s#.*QUnit.test( "\([a-zA-Z0-9_$]+\)", ( assert ) => {#	public void \1()#
s#^.*QUnit\.test.+"([a-zA-Z0-9_$]+)".+$#	@Test\n	public void \1()#