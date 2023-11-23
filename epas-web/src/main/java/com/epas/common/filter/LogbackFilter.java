package com.epas.common.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
* Write logs by excluding specific queries.
* 
* @since 20230724
* @author kjyoo
* @see <pre>
*  Class Name : LogbackFilter.java
*  Description : 
*
*  << Modification History >>
*  
 *  Date              Author             Description
*  ----------        -----------        ----------------------
*  20230724           kjyoo              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/
public class LogbackFilter extends Filter<ILoggingEvent>{
    @Override
    public FilterReply decide(ILoggingEvent event) {    
        if (event.getMessage().contains("/* NO_LOG_QUERY */")) {
            return FilterReply.DENY;
        }else{
            return FilterReply.ACCEPT;
        }
    }
}