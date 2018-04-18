package xyz.anana.pine.core.field;

import java.util.List;

import fr.iambluedev.pine.api.field.IField;
import fr.iambluedev.pine.api.field.IFields;
import lombok.AllArgsConstructor;

/**
 * @author <a href="https://twitter.com/iambluedev">iambluedev</a>
 */

@AllArgsConstructor
public class Fields implements IFields{

	private List<IField> fields;

	/**
	 * Get Fields
	 * 
	 * @return list of Field
	 */
	@Override
	public List<IField> getFields() {
		return this.fields;
	}
}
