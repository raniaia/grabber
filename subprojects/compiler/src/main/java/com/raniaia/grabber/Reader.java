package com.raniaia.grabber;

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
 * Creates on 2020/4/5.
 */

import com.raniaia.grabber.tools.GrabberCfg;
import org.raniaia.available.OpCodes;
import org.raniaia.available.io.file.Files;
import org.raniaia.available.list.Lists;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * 获取源码目录，并读取每个包下的所有源码文件。
 *
 * @author tiansheng
 */
public class Reader {

	String 					srcdir;
	List<String> 			classfile;
	List<String> 			packages;
	String 					previous;

	GrabberCfg grabc
			= GrabberCfg.getInstance(OpCodes.USER_DIR + "/config/compiler.cfg");

	String					OBJECT_FILE_SUFFIX	= grabc.getValue("reader","class");
	String					STMT_FILE_SUFFIX	= grabc.getValue("reader","structure-body");

	/**
	 * @param srcdir 源码目录
	 */
	public Reader(String srcdir) {
		this.srcdir = srcdir;
	}

	public void init() {
		File dirs = Files.newFile(srcdir);
		getPackages(dirs);
	}

	void getPackages(File files) {
		for (File file : Objects.requireNonNull(files.listFiles())) {
			//
			// 如果是一个目录就保存到previous
			//
			if (file.isDirectory()) {
				getPackages(file);
			}
			if (OBJECT_FILE_SUFFIX.equals(Files.getSuffix(file))
							|| STMT_FILE_SUFFIX.equals(Files.getSuffix(file))) {
				if (classfile == null) {
					classfile = Lists.newArrayList();
				}
				classfile.add(
						file.getPath().substring(srcdir.length())
				);
			}
		}
		if (classfile != null && !classfile.isEmpty()) {
			add(Lists.newArrayList(classfile));
			classfile.clear();
			previous = null;
		}
	}

	void add(List<String> files) {
		if (packages == null) {
			packages = Lists.newArrayList(files);
			return;
		}
		packages.addAll(Lists.newArrayList(files));
	}

	public List<GrabberSource> toSourceCodeList() {
		List<GrabberSource> codes = Lists.newArrayList();
		for (String aPackage : packages) {
			String key = srcdir.concat("\\").concat(aPackage);
			codes.add(new GrabberSource(key));
		}
		return codes;
	}

}