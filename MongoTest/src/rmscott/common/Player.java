package rmscott.common;

import rmscott.util.StringValidator;

public class Player extends Person implements java.io.Serializable {

	private static final long serialVersionUID = 2120698598068312636L;
	
	private String position = null;
	private float ranking = new Float(0.0).floatValue();
	private String notes = null;
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public float getRanking() {
		return ranking;
	}
	public void setRanking(float ranking) {
		this.ranking = ranking;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + Float.floatToIntBits(ranking);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (Float.floatToIntBits(ranking) != Float.floatToIntBits(other.ranking))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Player [position=");
		sb.append(position);
		sb.append(", ranking=");
		sb.append(ranking);
		sb.append(", notes=");
		sb.append(notes);
		sb.append("]");
		sb.append(StringValidator.EOL);
		sb.append("    ");
		sb.append(super.toString());
		
		return sb.toString();
	}	

}
