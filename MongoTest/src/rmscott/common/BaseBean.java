/**
 * RMSCOTT - prototyping
 */
package rmscott.common;

/**
 * @author rmscott
 *
 */
public class BaseBean implements java.io.Serializable {
	 
	private static final long serialVersionUID = -3081305268881713251L;
	
	String _id = null;

	/**
	 * Constructor for this class
	 */
	public BaseBean() {
		
	}
	
	public String get_id() {
		return _id;
	}



	public void set_id(String _id) {
		this._id = _id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseBean other = (BaseBean) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseBean [_id=" + _id + "]";
	}

}
