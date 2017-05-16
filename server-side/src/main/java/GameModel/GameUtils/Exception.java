package GameModel.GameUtils;

/**
 * Created by latiif on 5/16/17.
 */
public class Exception extends Throwable {

	private String name;
	private String message;

	public Exception(String name,String message){
		this.name=name;
		this.message=message;
	}

	public String getName(){
		return this.name;
	}

	public String getMessage(){
		return this.message;
	}

	@Override
	public String toString(){
		StringBuilder sb= new StringBuilder();

		sb.append(getName()+": "+getMessage());

		return sb.toString();
	}

}
