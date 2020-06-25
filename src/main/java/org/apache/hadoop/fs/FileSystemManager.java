/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.fs;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

@SuppressWarnings({"StaticVariableMayNotBeInitialized", "StaticVariableUsedBeforeInitialization"})
public final class FileSystemManager
{
    private FileSystemManager() {}

    private static FileSystemCache cache;

    @SuppressWarnings("ObjectEquality")
    public static synchronized void registerCache(FileSystemCache newCache)
    {
        requireNonNull(newCache, "cache is null");
        checkState(cache == null || cache == newCache, "FileSystem cache already registered");
        cache = newCache;
        FileSystem.CACHE = new DelegatedFileSystemCache(cache);
    }
}
