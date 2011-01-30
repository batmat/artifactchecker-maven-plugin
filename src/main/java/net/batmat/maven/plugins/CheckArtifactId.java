package net.batmat.maven.plugins;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Simple goal that will check if your project folder has the same name as the current project
 * artifactId.
 * 
 * @goal check-artifactId
 */
public class CheckArtifactId extends AbstractMojo
{
	/**
	 * @parameter default-value="${project.artifactId}
	 */
	private String artifactId;

	public void execute() throws MojoExecutionException
	{
		// find project directory more cleanly, injecting for example {project.xyz} ?
		String parentDirectory = getParentDirectory();
		if (!artifactId.equals(parentDirectory))
		{
			getLog()
					.warn("Different names between artifactId("
							+ artifactId
							+ ") and parent directory("
							+ parentDirectory
							+ "). "
							+ "Having the same is a good practice, you should consider renaming so that they matches.");
		}
		else
		{
			getLog().info("Good, project directory matches artifactId (" + (artifactId) + ")");
		}

	}

	/**
	 * Returns parent directory name (only last segment), starting from cwd.
	 * 
	 * @return parent directory name (only last segment), starting from cwd.
	 * 
	 *         FIXME : must improve the code, I'm not sure at all it's the best way to walk directory
	 *         structure + finding the starting point. For example : what will arrive if mvn is not
	 *         run from the CWD?
	 * @throws IOException
	 */
	private String getParentDirectory()
	{
		String parentDirectory = new File(".").getAbsoluteFile().getParentFile().getName();
		return parentDirectory;
	}
}
