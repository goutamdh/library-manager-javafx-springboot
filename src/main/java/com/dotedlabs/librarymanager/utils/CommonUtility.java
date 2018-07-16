/**
 * 
 */
package com.dotedlabs.librarymanager.utils;

import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

/**
 * @author sandeepknair
 *
 */
@Component
public class CommonUtility {
	/**
	 * Function to get content from resource bundle
	 * 
	 * @param key
	 * @return
	 */
	public static String getTextFromBundle(String key) {
		return ResourceBundle.getBundle(CommonConstants.RESOURCE_BUNDLE_PATH).getString(key);
	}
}
