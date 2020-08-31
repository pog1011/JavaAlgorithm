package ink.pog.test;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;




import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;

public class ServerApp {
	
	ServerSocket server=null;	//server 成员变量的作用是用来打开服务器的端口
	Socket socket=null;	//socket 成员变量的作用是用来与客户端建立套接字连接
	BufferedReader cin=null;	//cin 成员变量的作用是用来建立输入流
	PrintStream cout=null;	// cout 成员变量的作用是用来建立输出流
	ConnectSocket connect = null;
	static ArrayList<Client> clients = new ArrayList<Client>();// 存储客户列表信息
	

	protected Shell shell;
	private Text textPort;
	public Text textInformation;
	private Button btnNewButton_1;
	public List clientList;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerApp window = new ServerApp();
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
		shell.setText("聊天系统——服务器端");
		shell.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		lblNewLabel.setText("监听端口");
		
		textPort = new Text(shell, SWT.BORDER);
		textPort.setText("1234");
		textPort.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// 打开服务器端口
					server = new ServerSocket(Integer.parseInt(textPort.getText()));
					// 添加提示信息
					System.out.print("系统提示:聊天服务器系统开始启动...... \n");
					textInformation.append("系统提示:聊天服务器系统开始启动...... \n");
				} catch (IOException e1) { // 捕捉打开端口时可能产生的异常
					textInformation.append("服务器端口打开出错\n");
				}
				if (server != null) { // 如果端口打开成功		
						/*自定义线程对象*/
					ConnectSocket readThread=new ConnectSocket(); //this代表当前对象
						/*通过 start 方法，让线程进入可运行状态*/
					readThread.start();						
				}					
			}
		});
		
		btnNewButton.setText("开始监听");
		
		clientList = new List(shell, SWT.BORDER);
		GridData gd_clientList = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_clientList.widthHint = 100;
		clientList.setLayoutData(gd_clientList);
		
		textInformation = new Text(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.heightHint = 226;
		textInformation.setLayoutData(gd_text);
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		btnNewButton_1.setText("结束监听");
	}
	public boolean checkName(Client newClient) { //检查是否重复名字
		for (int i = 0; i < clients.size(); i++) {
			// 取出每一个连接对象元素
			Client c = (Client) clients.get(i);
			// 如果不是对象自身但名字相同，则表明出现了重复名称
			if ((c != newClient) && (c.name).equals(newClient.name))
				return false;
		}
		return true;
		}
	
	public void notifyRoom() {  //已连接的客户名称发送到所有客户端。
		// 服务器发送到客户的信息字符串，“PEOPLE”表示是客户列表信息
		String people = "PEOPLE";
		for (int i = 0; i < clients.size(); i++) {
			// 获取每一个服务器—客户交互信息线程
			Client c = (Client) clients.get(i);
			// 将用户名称添加到发送信息字符串
			people += ":" + c.name;
		}
		// 调用信息广播成员方法，将用户列表信息发送到所有客户端
		sendClients(people);
	}
	
	public void sendClients(String msg) {  //群发msg消息
		for (int i = 0; i < clients.size(); i++) {
			// 获取每一个服务器—客户交互信息线程
			Client c = (Client) clients.get(i);
			// 通过线程的发送方法将信息发送出去/
			c.send(msg);
		}
	}

	public void sendAssignClients(String msg, String acceptName) {
		for (int i = 0; i < clients.size(); i++) {
			// 获取每一个服务器—客户交互信息线程
			Client c = (Client) clients.get(i);
			// 通过线程的发送方法将信息发送出去/
			String name = c.name;
			String sendName = msg.substring(msg.indexOf(":") + 1, msg.lastIndexOf(":"));
			if(name.equals(acceptName) || name.equals(sendName)){
				c.send(msg);
			}

		}
	}

	public void sendAssignFile(String path, String filename, String acceptName) {
		for (int i = 0; i < clients.size(); i++) {
			// 获取每一个服务器—客户交互信息线程
			Client c = (Client) clients.get(i);
			// 通过线程的发送方法将信息发送出去/
			String name = c.name;
			if(name.equals(acceptName)){
				String msg = "FILE:" + filename;
				c.send(msg);
				Socket socket = c.socket;
				 FileInputStream fis = null;
				 DataOutputStream dos = null;
				File file = new File(path + File.separator + filename);
				try {
					if (file.exists()) {
						fis = new FileInputStream(file);
						dos = new DataOutputStream(socket.getOutputStream());
						String str = "FILE:" + filename + ":ASSIGN:" + acceptName;
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
						file.delete();
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
	}

	
	public void disconnect(Client c) {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					appendInformation(c.name + "断开连接\n");					
					clients.remove(c);
					//服务器更新用户列表 界面
					clientList.removeAll();
					for(Client kehu:clients)
						clientList.add(kehu.name);	
					//向客户端发送最新的用户列表
					notifyRoom();
					// 向客户发送断开信息
					c.send("QUIT");					
					c.socket.close();// 断开连接
				} catch (IOException e) {
					appendInformation("客户断开错误\n");
				}
				}
			}
		);
	}
	public void appendInformation(String str) {//显示到消息栏
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				textInformation.append(str);
			}
			});
	}

	
	//内部类，  接收多客户端的线程类
	class ConnectSocket extends Thread {
		// 方法作用是在线程内部异步更新主界面的图形组件
		public void appendInformation() {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					/*
					 * 客户连接成功，定义并实例化一个 Client 线程， 每一个线程对应一个客户连接
					 */
					Client c = new Client(socket);
					
					/*
					 * 测该用户名称是否存在，如不存在，则启动线程， 实现客户与服务器的信 息 交互通道
					 */
					if (checkName(c)) {
						/* 将连接的客户添加到客户列表存储 clients 中 */						
						clients.add(c);
						//更新用户列表
						clientList.removeAll();
						for(Client kehu:clients)
							clientList.add(kehu.name);						
						// 启动线程
						c.start();
						/* 向每个客户端更新客户列表信息 */
						notifyRoom();
						
					} else {
						disconnect(c);
					}
				}
			});
		}

		Socket socket;

		public void run() {
			while (true) {
				while (true) {
					try {
						socket = server.accept();// 等待客户连接
					} catch (IOException e2) {
						textInformation.append("客户连接失败\n");
					}
					this.appendInformation();
				}
			}
		}
	}
	
	class ReadMessageThread extends Thread {
		/*
		 * 覆盖 Thread 类的 run 方法，在该方法中，循环从端口读入信息， 直到读入“QUIT”，则关闭套接字
		 */
		BufferedReader cin; // 输入流成员变量
		PrintStream cout; // 输出流成员变量
		Socket socket; // 套接字成员变量

		public void appendInformation(String str) {
			final String str1 = str;
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					textInformation.append(str1);
				}
			});
		}

		ReadMessageThread(Socket socket) {
			this.socket = socket;
			try {
				cin = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				cout = new PrintStream(this.socket.getOutputStream());
			} catch (IOException e) {
				this.appendInformation("输入输出流建立异常\n");
			}
		}

		public void run(){
			String str = "";
			while (true) {
				try {
					str = cin.readLine(); // 读入信息
				} catch (IOException e) {
					this.appendInformation("输入输出异常\n");
				}
				if (str.equals("QUIT")) { // 若为“QUIT”，则关闭套接字，跳出循环
					try {
						socket.close();
						this.appendInformation("用户连接已关闭\n");
					} catch (IOException e) {
						this.appendInformation("套接字关闭异常\n");
					}
					break;
				} else // 不是 QUIT 信息，则将读入的信息进行显示
					this.appendInformation("从客户端读入如下的信息:" + str + "\n");
			}
		}
	}
	
	// Client 类作为 ServerApp 类的内部类，
	class Client extends Thread {
		String name; // 用来存储客户的连接姓名
		BufferedReader dis; // 用于实现接受从客户端发送来的数据流
		PrintStream ps; // 用来实现向客户端发送信息的打印流
		Socket socket; // 用于建立套接字
		boolean isRun = true;// 控制 Client 线程运行状态
		// 采用异步方式更新主界面中的图形组件

		public void appendConnectionArea(String str) {//显示信息到消息栏
			final String str1 = str;
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					textInformation.append(str1);
				}
			});
		}

		public Client(Socket s) { // Client 线程的构造器
			socket = s; // 将服务器-客户端建立连接所形成的套接字传递到该线程
			try {
				// 存储特定客户 socket 的输入流，接受客户发送到服务器端的信息
				dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 存储特定客户的输出流，发送服务器信息给客户*/
				ps = new PrintStream(socket.getOutputStream());
				// 读取接收到的信息，该信息为客户登陆信息
				String info = dis.readLine();
				// 将信息用" ： "分离
				StringTokenizer stinfo = new StringTokenizer(info, ":");
				// 用 head 存储关键区分字
				String head = stinfo.nextToken();
				// 第二个数据段是客户的名称
				name = stinfo.nextToken();
				appendConnectionArea("系统消息：" + name + "已经连接\n");
			} catch (IOException e) {
				appendConnectionArea("系统消息：用户连接出错\n");
			}
		}

		// 实现向客户端发送信息的方法
		public void send(String msg) {
			ps.println(msg);
			ps.flush();
		}

		// 读取客户端发送过来的信息
		public void run() {
			while (isRun) {
				String line = null;
				try {
					/* 读取客户端发送的信息 */
					line = dis.readLine();
				} catch (IOException e) {
					// 如果出错，则要关闭连接，并更新客户列表信息
					appendConnectionArea("系统消息：读客户信息出错");
					disconnect(this);
					notifyRoom();
					return;
				}
				System.out.println(line); //输出到控制台，调试
				// 对读入的信息进行分离，以确定信息类型
				StringTokenizer st = new StringTokenizer(line, ":");
				String keyword = st.nextToken();// 关键字，判断消息类型
				System.out.println(keyword); //输出到控制台，调试
				//对PEOPLE 类型消息 要处理   列表控件要更新
				if (keyword.equalsIgnoreCase("MSG")) {
					/*
					 * 将接收到的客户聊天信息，通过调用信息广播成员方法， 发送到所有客户端
					 */
					sendClients(line);
					appendConnectionArea(line); //显示消息到信息面板
					// 如果关键字是 QUIT，则是客户端发送的退出信息
				} else if (keyword.equalsIgnoreCase("QUIT")) {					
					// 关闭连接，并更新客户列表信息
					disconnect(this);					
					// 结束当前线程
					isRun = false;
				} else if(keyword.equalsIgnoreCase("ASSIGN")){
					String msg = line.substring(line.indexOf(";") + 1);
					String acceptName = line.substring(line.indexOf(":") + 1,line.indexOf(";"));
					sendAssignClients(msg, acceptName);
				}else if(keyword.equalsIgnoreCase("FILE")) {
					//file:filename:assign:name
					String fileName = line.substring(line.indexOf(":") + 1, line.indexOf(":", line.indexOf(":") + 1));
					String acceptName = line.substring(line.lastIndexOf(":") + 1);
					System.out.println(fileName);
					System.out.println(acceptName);
					String path = "C:\\socketTemp";
					File directory = new File(path);
					if(!directory.exists()){
						directory.mkdir();
					}
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
						sendAssignFile(path,fileName,acceptName);
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
			}
		}
	}
}
