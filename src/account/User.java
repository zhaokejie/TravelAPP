package account;

import java.util.ArrayList;

import friend.Friend_Sql;

public class User{
	private String uid;
	private	String name;
	String headPicSrc;
	private ArrayList<String> friends;
	
		public User(String n)
		{
			this.setName("-1");
		}
		public User(String n,String uid,ArrayList<String> frienduser)
		{
			this.setName(n);
			this.uid = uid;
			this.addAllFriend(frienduser);
		}
		public User(String n,String uid,String headPicSrc)
		{
			this.setName(n);
			this.uid = uid;
			this.headPicSrc = headPicSrc;
		}
		public User(String n,String uid,String headPicSrc,ArrayList<String> frienduser)
		{
			this.setName(n);
			this.uid = uid;
			this.headPicSrc = headPicSrc;
			this.friends = frienduser;

		}
//		public User(String n,String p)
//		{
//			this.setName(n);
//		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getName() {
			return name;
		}

		public String getheadPicSrc() {
			return headPicSrc;
		}
		public void setName(String name) {
			this.name = name;
		}

		public void addFriend(String aUser)
		{
			this.friends.add(aUser);
		}
		public void addAllFriend(ArrayList<String> user)
		{
			this.friends = user;
		}
		public ArrayList<String> getAllFriend()
		{
			return this.friends;
		}
	}
