package com.raniaia.grabber.tools;

/*
 * Copyright (C) 2020 the original author or authors.
 * Licensed under the GPL, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://b2evolution.net/about/gnu-gpl-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The source code of this program is only provided for learning and research.
 * If the program source code is used for criminal acts, such as illegal acts,
 * it is not related to the original author and need to be personally responsible.
 */

/*
 * Creates on 2020/4/10.
 */

import org.raniaia.available.config.Cfg;

import java.io.IOException;

/**
 * 这个是配置类，Grabberc全称是Grabber配置。
 *（Grabber Config）
 *
 * @author tiansheng
 */
public class GrabberCfg {

	public static GrabberCfg getInstance(String path) {
		return getInstance(path,null);
	}

	public static GrabberCfg getInstance(String path, Class<?> clazz) {
		return new GrabberCfg(path,clazz);
	}

	Cfg cfg;

	GrabberCfg(String path, Class<?> clazz) {
		try {
			this.cfg = new Cfg(path,clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getValue(String root,String key) {
		return cfg.get(root, key);
	}

}