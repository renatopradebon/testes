<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/webresources/common/header.jspf"%>

<div id="wrapper">
    <%@ include file="/webresources/common/navigationbar.jspf"%>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5><i class="fa fa-edit fa-fw"></i> <b> Update Customer</b></h5> 
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form role="form" action="${appPath}/customer/update" method="POST">
                                    <input type="hidden" name="id" value="${CUSTOMER.id}" />
                                    <div class="form-group">
                                        <label for="firstName">First Name</label>
                                        <input class="form-control" type="text" name="firstName"  value="${CUSTOMER.firstName}" />
                                    </div>     
                                    <div class="form-group">
                                        <label for="lastName">Last Name</label>
                                        <input class="form-control" type="text" name="lastName"  value="${CUSTOMER.lastName}" />
                                    </div>     
                                    <div class="form-group">
                                        <label for="age">Age</label>
                                        <input class="form-control" type="text" name="age"  value="${CUSTOMER.age}" />
                                    </div>     
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-check fa-fw"></i>Update</button>
                                    <a href="${appPath}/customer/list" class="btn btn-default"><i class="fa fa-close fa-fw"></i>Cancel</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--end content--%>
<%@ include file="/webresources/common/footer.jspf"%>
