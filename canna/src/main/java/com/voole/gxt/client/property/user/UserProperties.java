package com.voole.gxt.client.property.user;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.user.User;
import com.voole.gxt.shared.entity.user.UserGroup;

public interface UserProperties extends CannaPropertyAccess<User> {
	ValueProvider<User, String> email();

	ValueProvider<User, String> password();

	ValueProvider<User, String> name();

	ValueProvider<User, String> mobile();

	ValueProvider<User, String> extNum();

	ValueProvider<User, UserGroup> group();

}
