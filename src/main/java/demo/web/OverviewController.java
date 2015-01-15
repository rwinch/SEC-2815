package demo.web;

/**
 * Copyright: Copyright (c) 2002-2014
 * Company: Reflexion Networks, Inc.
 * Created: August 2014
 *
 * @author droyer@reflexion.net
 */

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.web.services.DataCenterService;

@Controller
@RequestMapping(
    value = "/details" )
public class OverviewController
{

    private static final Logger logger = LoggerFactory.getLogger( OverviewController.class );

    @Autowired
    DataCenterService dataCenterService;

    final ObjectMapper mapper = new ObjectMapper( );

    @RequestMapping(
        method = RequestMethod.GET )
    public String showOverview (
        final Model model,
        final UUID requestUUID )
    {
        logger.debug(
            "[OVERVIEW] Showing the monitor overview page. Request-UUID=[{}].",
            requestUUID );

        //
        // Serialize the overview data into JSON so the Javascript can pick it
        // up on page load.
        //
        final String initialStateJson = "";

        // This data is injected into a <script> tag unescaped. The "type"
        // attribute of the tag is set to "application/json", so the data is not
        // executed by the browser. Also, any content should be escaped by the
        // Jackson mapper, so this should be safe, valid JSON.
        model.addAttribute( "initial", initialStateJson );

        return "overview";
    }
}
