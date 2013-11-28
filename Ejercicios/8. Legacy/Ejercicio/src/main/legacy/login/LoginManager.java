package legacy.login;

import java.util.Hashtable;

public class LoginManager {
	private Hashtable m_users = new Hashtable();

    public boolean IsLoginOK(String user, String password)
    {
        try
        {
           StaticLogger.Write("blah");
        }
        catch (LoggerException e)
        {
            StaticWebService.Write(e.getMessage() + Environment.getMachineName());
        }
        if (m_users.get(user)!= null &&
            (String) m_users.get(user) == password)
        {
            return true;
        }
        return false;
    }
}
