package diapositivas.designfortesteability;

public class DiggingCollaborators {

	public boolean isAdmin(){
		return getUserManager()
				.getUser(1)
				.profile()
				.isInRole(Roles.Admin);
	}

	private boolean isInRole(Roles admin) {
		// TODO Auto-generated method stub
		return false;
	}

	private DiggingCollaborators profile() {
		// TODO Auto-generated method stub
		return null;
	}

	private DiggingCollaborators getUser(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private DiggingCollaborators getUserManager() {
		// TODO Auto-generated method stub
		return this;
	}
}
