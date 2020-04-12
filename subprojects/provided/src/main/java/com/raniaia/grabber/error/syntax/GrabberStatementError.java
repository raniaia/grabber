package com.raniaia.grabber.error.syntax;

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
 * Creates on 2020/4/12.
 */

import com.raniaia.grabber.error.GrabberError;

/**
 * @author tiansheng
 */
public class GrabberStatementError extends GrabberError {

	public GrabberStatementError() {
	}

	public GrabberStatementError(String message) {
		super(message);
	}

	public GrabberStatementError(String message, Throwable cause) {
		super(message, cause);
	}

	public GrabberStatementError(Throwable cause) {
		super(cause);
	}

	public GrabberStatementError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}