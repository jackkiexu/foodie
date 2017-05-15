package com.lami.foodie.utils.ftp;

/**
 * Created by xujiankang on 2017/5/15.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * sftp 安装
 * http://www.jianshu.com/p/8da79294a46f
 *
 */
public class DemoSftp {

    public static void main(String[] args) throws Exception, SftpException {

        String hostname = "192.168.0.65";
        String login = "andy";
        String password = "andy";
        String directory = "/";

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");

        JSch ssh = new JSch();
        Session session = ssh.getSession(login, hostname, 22);
        session.setConfig(config);
        session.setPassword(password);
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();

        ChannelSftp sftp = (ChannelSftp) channel;
        sftp.cd(directory);
        Vector<ChannelSftp.LsEntry> files = sftp.ls("*");
        System.out.printf("Found %d files in dir %s%n", files.size(), directory);

        for (ChannelSftp.LsEntry file : files) {
            if (file.getAttrs().isDir()) {
                continue;
            }
            System.out.printf("Reading file : %s%n", file.getFilename());
            BufferedReader bis = new BufferedReader(new InputStreamReader(sftp.get(file.getFilename())));
            String line = null;
            while ((line = bis.readLine()) != null) {
                System.out.println(line);
            }
            bis.close();
        }

        channel.disconnect();
        session.disconnect();

    }

}
