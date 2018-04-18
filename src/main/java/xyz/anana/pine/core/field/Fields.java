package xyz.hugol.pine.core.field;

import java.util.List;

import fr.iambluedev.pine.api.field.IField;
import fr.iambluedev.pine.api.field.IFields;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Fields implements IFields{

	private List<IField> fields;

	@Override
	public List<IField> getFields() {
		return this.fields;
	}
}
