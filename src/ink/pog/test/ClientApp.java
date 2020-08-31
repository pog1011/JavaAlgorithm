package ink.pog.test;


import com.sun.xml.internal.ws.util.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ClientApp {
    Socket socket = null;    //socket 成员变量的作用是用来与服务器端建立套接字连接
    BufferedReader cin = null;//cin 成员变量的作用是用来建立输入流
    PrintStream cout = null;    // cout 成员变量的作用是用来建立输出流
    private FileInputStream fis;
    private DataOutputStream dos;
    protected Shell shell;
    private Text ipAddress;
    private Text textPort;
    private Text talkMessage;
    private String acceptName;
    private Text textInformation;
    private Button disButton;
    private Label lblNewLabel_2;
    private Text textClientName;
    private Label lblNewLabel_3;
    private List clientsList;
    private FileDialog fileDialog;

    String clientName = ""; //用于存储客户登陆名称

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ClientApp window = new ClientApp();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("聊天系统——客户端");
        shell.setLayout(new GridLayout(7, false));

        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

        lblNewLabel.setText("服务器IP");

        ipAddress = new Text(shell, SWT.BORDER);
        ipAddress.setText("127.0.0.1");
        GridData gd_ipAddress = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gd_ipAddress.widthHint = 150;
        ipAddress.setLayoutData(gd_ipAddress);

        Label lblNewLabel_1 = new Label(shell, SWT.NONE);
        lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

        lblNewLabel_1.setText("端口号");

        textPort = new Text(shell, SWT.BORDER);
        textPort.setText("1234");
        GridData gd_textPort = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gd_textPort.widthHint = 80;
        textPort.setLayoutData(gd_textPort);

        lblNewLabel_2 = new Label(shell, SWT.NONE);
        lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblNewLabel_2.setText("客户名称");

        textClientName = new Text(shell, SWT.BORDER);
        textClientName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Button ConnectButton = new Button(shell, SWT.NONE);
        ConnectButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    // 获得服务器 IP
                    InetAddress ip = InetAddress.getByName(ipAddress.getText());
                    // 获得服务器端口
                    int port = Integer.parseInt(textPort.getText());
                    // 尝试与服务器进行连接，建立套接字
                    socket = new Socket(ip, port);
                    textInformation.append("系统提示:与聊天服务器系统开始连接...... \n");
                } catch (IOException e1) { // 捕捉套接字建立时可能产生的异常
                    textInformation.append("服务器端口打开出错\n");
                }
                if (socket != null) { // 套接字建立成功
                    textInformation.append("系统提示:与服务器连接成功...... \n");
                    clientName = textClientName.getText().trim(); //获得客户连接的名称
                    try {
                        cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        cout = new PrintStream(socket.getOutputStream());
                        //构建客户向服务器发送连接请求的信息，并发送到服务器
                        String str = "PEOPLE:" + clientName;
                        cout.println(str);
                        /* 自定义线程对象 */
                        ReadMessageThread readThread = new ReadMessageThread();
                        /* 通过 start 方法，让线程进入可运行状态 */
                        readThread.start();
                    } catch (IOException e3) { // 捕捉可能产生的异常
                        textInformation.append("输入输出异常\n");
                    }
                }


            }
        });

        ConnectButton.setText("连接服务器");

        lblNewLabel_3 = new Label(shell, SWT.NONE);
        lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblNewLabel_3.setText("聊天消息");

        talkMessage = new Text(shell, SWT.BORDER);
        GridData gd_talkMessage = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
        gd_talkMessage.horizontalIndent = 1;
        gd_talkMessage.verticalIndent = 3;
        talkMessage.setLayoutData(gd_talkMessage);

        Button sendButton = new Button(shell, SWT.NONE);
        sendButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String str = talkMessage.getText();
                if (acceptName != null && !acceptName.trim().equals("")) {
                    str = "ASSIGN:" + acceptName + ";MSG:" + clientName + ":" + str;
                } else {
                    str = "MSG:" + clientName + ":" + str;
                }
                cout.println(str);
            }
        });

        sendButton.setText("发送消息");

        disButton = new Button(shell, SWT.NONE);
        disButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                /*按约定格式构建客户向服务器请求断开的信息，并发送*/
                cout.println("QUIT");
                textInformation.append("客户请求断开连接\n");
                clientsList.removeAll();
            }
        });

        disButton.setText("断开服务器");

        clientsList = new List(shell, SWT.BORDER);
        clientsList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1));
        clientsList.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String[] selection = clientsList.getSelection();
                if(selection != null && selection.length > 0){
                    acceptName = selection[0];
                    if (clientName.equals(acceptName)) {
                        acceptName = null;
                    }
                }
            }
        });


        textInformation = new Text(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        textInformation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));

        fileDialog = new FileDialog(shell);

        Button SendFileButton = new Button(shell, SWT.NONE);
        SendFileButton.setBounds(21, 224, 80, 27);
        SendFileButton.setText("发送文件");
        SendFileButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (acceptName != null && !acceptName.trim().equals("")) {
                    fileDialog.open();
                    String fileName = fileDialog.getFileName();
                    String path = fileDialog.getFilterPath();
                    File file = new File(path + File.separator + fileName);
                    try {
                        if (file.exists()) {
                            fis = new FileInputStream(file);
                            dos = new DataOutputStream(socket.getOutputStream());
                            String str = "FILE:" + fileName + ":ASSIGN:" + acceptName;
                            // 文件名
                            cout.println(str);
                            // 开始传输文件
                            byte[] bytes = new byte[1024];
                            int length = 0;
                            long progress = 0;
                            while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                                dos.write(bytes, 0, length);
                                dos.flush();
                                progress += length;
                            }
                        }
                    } catch (Exception ee) {
                    } finally {
                        try {
                            if (fis != null)
                                fis.close();
                            if (dos != null)
                                dos.close();
                        } catch (Exception eee) {
                        }
                    }
                }
            }
        });


    }

    //内部类
    class ReadMessageThread extends Thread {
        public void list(final ArrayList<String> imessage) {
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                    clientsList.removeAll();
                    for (String temp : imessage)
                        clientsList.add(temp);
                }
            });
        }

        public void appendTextArea(String str) {
            final String str1 = str;
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                    textInformation.append(str1);
                }
            });
        }

        public void acceptFile(String fileName){
            String path = "C:\\socketClientTemp";
            File directory = new File(path);
            if(!directory.exists()){
                directory.mkdir();
            }
            System.out.println(fileName);
            File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
            DataInputStream dataInputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                fileOutputStream = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length = 0;
                while((length = dataInputStream.read(bytes, 0, bytes.length)) != -1) {
                    fileOutputStream.write(bytes, 0, length);
                    fileOutputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(fileOutputStream != null)
                        fileOutputStream.close();
                    if(dataInputStream != null)
                        dataInputStream.close();
                } catch (Exception e) {}
            }
        }

        boolean isRun = true; //标记socket 是否要有效

        public void run() {
            String line = "";
            while (isRun) {
                try {
                    line = cin.readLine(); // 从端口读入一条信息
                } catch (IOException e) {
                    this.appendTextArea("输入输出异常\n");
                }
                StringTokenizer st = new StringTokenizer(line, ":");
                String keyword = st.nextToken();// 存储关键字，判断消息类型
                if (keyword.equalsIgnoreCase("QUIT")) { // 服务器同意断开信息
                    try {
                        socket.close();
                        this.appendTextArea("接收到服务器同意断开信息，套接字关闭\n");
                    } catch (IOException e) {
                        this.appendTextArea("套接字关闭异常\n");
                    }
                    isRun = false;
                } else if (keyword.equalsIgnoreCase("PEOPLE")) { // 客户列表信息
                    /* 将客户名称分离到 Vector 中，然后将其显示在下拉列表中 */
                    ArrayList<String> imessage = new ArrayList<String>();
                    while (st.hasMoreTokens())
                        imessage.add(st.nextToken());
                    this.list(imessage);
                }
                else if (keyword.equalsIgnoreCase("FILE")) {
                    String fileName = line.substring(line.indexOf(":") + 1);
                    String msg =  "收到文件:" + fileName;
                    this.appendTextArea(msg + "\n");
                    acceptFile(fileName);
                } else {
                    // 接收的是来自服务器的广播信息
                    // 将信息的余下内容全部提取，并去掉首字符（冒号），并显示
                    String message = st.nextToken("\0");
                    message = message.substring(1);
                    this.appendTextArea(message + "\n");
                }
            }
        }
    }

}
