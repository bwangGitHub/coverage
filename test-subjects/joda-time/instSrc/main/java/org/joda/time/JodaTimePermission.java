/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time;

import java.security.BasicPermission;

/**
 * JodaTimePermission is used for securing global method calls in the Joda-Time
 * library. Since this class extends BasicPermission, asterisks may be used to
 * denote wildcard permissions. The following permissions are supported:
 *
 * <pre>
 * DateTimeZone
 *   .setDefault                 Allows a default DateTimeZone to be set
 *   .setProvider                Allows the DateTimeZone instance provider to be set
 *   .setNameProvider            Allows the DateTimeZone name provider to be set
 *
 * ConverterManager
 *   .alterInstantConverters     Allows an instant converter to be added or removed
 *   .alterPartialConverters     Allows a partial converter to be added or removed
 *   .alterDurationConverters    Allows a duration converter to be added or removed
 *   .alterPeriodConverters      Allows a period converter to be added or removed
 *   .alterIntervalConverters    Allows an interval converter to be added or removed
 *
 * CurrentTime.setProvider       Allows the current time provider to be set
 * </pre>
 * <p>
 * JodaTimePermission is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public class JodaTimePermission extends BasicPermission {
  static {
    CodeCoverCoverageCounter$2eum76covqw69aenq7k9imz2ip9krdr57o0x.ping();
  }

    
    /** Serialization version */
    private static final long serialVersionUID = 1408944367355875472L;
  static {
    CodeCoverCoverageCounter$2eum76covqw69aenq7k9imz2ip9krdr57o0x.statements[1]++;
  }

    /**
     * Constructs a new permission object.
     * 
     * @param name  the permission name
     */
    public JodaTimePermission(String name) {
        super(name);
CodeCoverCoverageCounter$2eum76covqw69aenq7k9imz2ip9krdr57o0x.statements[2]++;
    }

}

class CodeCoverCoverageCounter$2eum76covqw69aenq7k9imz2ip9krdr57o0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2eum76covqw69aenq7k9imz2ip9krdr57o0x ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2eum76covqw69aenq7k9imz2ip9krdr57o0x () {
    super("org.joda.time.JodaTimePermission.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.JodaTimePermission.java");
      for (int i = 1; i <= 2; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
      for (int i = 1; i <= 0; i++) {
        if (loops[i * 3 - 2] != 0L) {
          log.passCounter("L" + i + "-0", loops[i * 3 - 2]);
          loops[i * 3 - 2] = 0L;
        }
        if ( loops[i * 3 - 1] != 0L) {
          log.passCounter("L" + i + "-1", loops[i * 3 - 1]);
          loops[i * 3 - 1] = 0L;
        }
        if ( loops[i * 3] != 0L) {
          log.passCounter("L" + i + "-2", loops[i * 3]);
          loops[i * 3] = 0L;
        }
      }
  }
}

