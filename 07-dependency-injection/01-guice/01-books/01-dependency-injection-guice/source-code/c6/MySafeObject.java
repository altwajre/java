/**
 * Copyright (C) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package c6;

import net.jcip.annotations.Immutable;
import com.google.inject.Singleton;
import com.google.inject.Inject;
import c6.Dependency;

@Immutable @Singleton
public class MySafeObject{
	private final Dependency dep1;
	private final Dependency dep2;

	@Inject
	public MySafeObject(Dependency dep1, Dependency dep2) {
		this.dep1 = dep1;
		this.dep2 = dep2;
	}

	public Dependency getDep1() { return dep1; }

	public Dependency getDep2() { return dep2; }

}
