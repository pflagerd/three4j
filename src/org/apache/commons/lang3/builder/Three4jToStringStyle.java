package org.apache.commons.lang3.builder;

@SuppressWarnings("serial")
public class Three4jToStringStyle extends ToStringStyle {
	public Three4jToStringStyle() {
		this.setUseShortClassName(true);
		this.setUseIdentityHashCode(false);
		this.setArraySeparator(", ");
		this.setArrayStart("[");
		this.setArrayEnd("]");
		this.setContentStart(" {");
		this.setContentEnd("}");
		this.setNullText("null");
		this.setFieldNameValueSeparator(": ");
		this.setFieldSeparator(", ");
	}

	public static Three4jToStringStyle THREE4J_STYLE = new Three4jToStringStyle();

	@Override
	protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
		if (value instanceof String) {
			fieldName = fieldName.substring(1);
			buffer.append("\"" + value + "\"");
			return;
		} else if (value instanceof Double) {
			double d = ((Double)value).doubleValue();
			if (d % 1 == 0) {
				value = String.format("%.0f", d);
			}
		}
		buffer.append(value);
	}

	@Override
	protected void appendDetail(StringBuffer buffer, String fieldName, double value) {
		if (value % 1 == 0) {
			buffer.append(String.format("%.0f", value));
			return;
		}
		buffer.append(value);
	}

	@Override
	public void setUseFieldNames(boolean value) {
		super.setUseFieldNames(value);
	}

}

