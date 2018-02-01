<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contenidos Videos</title>
</head>
<body>
	<form name="f1" method="post" enctype="multipart/form-data"	action="FileUploadBean">
		<h2>Add Image In DB</h2>
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td>Name:</td>
							<td><input type="text" name="txtName" value="" /></td>
						</tr>
						<tr>
							<td>Address:</td>
							<td><input type="text" name="txtAddress" value="" /></td>
						</tr>
						<tr>
							<td>Salary:</td>
							<td><input type="text" name="txtSalary" value="" /></td>
						</tr>
						<tr>
							<td>Photo:</td>
							<td><input type="file" name="flPhoto" value="" /></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><input type="submit"
								value="Save" name="btnSave" /></td>
						</tr>
					</table>
				</td>
				<td>
					<table>
						<tr>
							<td style="width: 450px; height: auto;"><iframe name="ifs"
									src="DisplayData" style="width: 550px; height: 300px"></iframe>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>