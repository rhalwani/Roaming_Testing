/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author Riad
 */
@Stateless
public class HLRCommander
{
  private final int PGWport = 7776;
  private final String PGWIP = "192.167.0.12";
  private final String cmdEnd = "---    END";
  private final String user = "test";
  private final String password = "test123";
  private final String NEWLINEREPLACE = "<br />";
  private BufferedWriter bufout;
  private BufferedReader bufin;
  private Socket sock;
  private InputStream is;
  private String cmdOutput;

  public String getCmdOutput()
  {
    return this.cmdOutput;
  }

  private void setCmdOutput(String cmdOutput) {
    this.cmdOutput = cmdOutput;
  }

  private String getPassword() {
    return "test123";
  }

  private String getUser()
  {
    return "test";
  }

  private void login()
  {
    try {
      this.sock = new Socket(InetAddress.getByName("192.167.0.12"), 7776);
      this.bufout = new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream()));
      this.is = this.sock.getInputStream();
      this.bufin = new BufferedReader(new InputStreamReader(this.is));

      this.bufout.write(new StringBuilder().append("LGI: HLRSN =1, OPNAME=\"").append(getUser()).append("\", PWD=\"").append(getPassword()).append("\";").toString());
      this.bufout.flush();

      while (this.is.available() == 0) {
        Thread.sleep(1000L);
      }
      String readLine;
      do
        readLine = new StringBuilder().append(this.bufin.readLine()).append("\n").toString();
      while (!readLine.contains("---    END".subSequence(0, "---    END".length() - 1)));
    }
    catch (InterruptedException ie) {
      System.err.println("Interrupted Exception during login.");
    } catch (IOException ie) {
      System.err.println("IO Exception during login.");
    }
  }

  private void logout() {
    try {
      this.bufout.write("LGO:;");
      this.bufout.flush();

      while (this.is.available() == 0) {
        Thread.sleep(1000L);
      }
      String readLine;
      do
        readLine = new StringBuilder().append(this.bufin.readLine()).append("\n").toString();
      while (!readLine.contains("---    END".subSequence(0, "---    END".length() - 1)));
    }
    catch (InterruptedException ie) {
      System.err.println("Interrupted Exception during logout.");
    } catch (IOException ie) {
      System.err.println("IO Exception during logout.");
    }
  }

  private void disconnectFromPGW() {
    try {
      logout();
      this.bufout.close();
      this.bufin.close();
      this.sock.close();
    } catch (IOException io) {
      System.err.println("Could not disconenect from PGW.");
    }
  }

  public void executeCommand(ArrayList<String> cliCmd)
  {
    setCmdOutput(null);

    login();

    StringBuilder pgwReply = new StringBuilder();
    try
    {
      for (String cmdString : cliCmd) {
        this.bufout.write(cmdString);
        this.bufout.flush();
        while (this.is.available() == 0)
          Thread.sleep(1000L);
        String readLine;
        do
        {
          readLine = this.bufin.readLine().concat("<br />");
          pgwReply.append(readLine);
        }while (!readLine.contains("---    END".subSequence(0, "---    END".length() - 1)));
      }

      setCmdOutput(pgwReply.toString());
      disconnectFromPGW();
    }
    catch (UnknownHostException unknownHost) {
      System.err.println("Unknown Host");
    } catch (IOException io) {
      System.err.println("IO Exception");
    }
    catch (InterruptedException i) {
      System.err.println("Interrupted Exception");
    }
  }

  public void testExec(ArrayList<String> cliCmd) {
    setCmdOutput(null);
    String out = "";
    for (String cmdString : cliCmd) {
      out = out.concat(cmdString.concat("\n"));
    }
    setCmdOutput(out);
  }
}