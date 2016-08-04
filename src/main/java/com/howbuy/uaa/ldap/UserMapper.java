package com.howbuy.uaa.ldap;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

public class UserMapper implements AttributesMapper<User> {

	@Override
	public User mapFromAttributes(Attributes attributes) throws NamingException {

		User user = new User();
		Attribute attributeCn = attributes.get("cn");
		if (null != attributeCn) {
			user.setUserName(attributeCn.get().toString());
		}
		Attribute attributeAcc = attributes.get("sAMAccountName");
		if (null != attributeAcc) {
			user.setAccount(attributeAcc.get().toString());
		}
		Attribute attributeMail = attributes.get("mail");
		if (null != attributeMail) {
			user.setEmail(attributeMail.get().toString());
		}
		Attribute attributeUid = attributes.get("member");
		if (null != attributeUid) {
			int size = attributeUid.size();
			List<String> memberList = user.getMemberList();
			for (int i = 0; i < size; i++) {
				String ms = attributeUid.get(i).toString();
				memberList.add(ms);
			}
		}
		
		Attribute attributeDistinguishedName = attributes.get("distinguishedName");
		if(null!=attributeDistinguishedName){
			user.setDistinguishedName(attributeDistinguishedName.get().toString());
		}else{
			return null;
		}
		return user;
	}

}
