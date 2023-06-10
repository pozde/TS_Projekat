import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Container from "@mui/material/Container";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";
import AdbIcon from "@mui/icons-material/Adb";
import { Link } from "react-router-dom";
import MovieIcon from "@mui/icons-material/Movie";
import SearchIcon from "@mui/icons-material/Search";
import NotificationsIcon from "@mui/icons-material/Notifications";
import Popper from "@mui/material/Popper";
import Fade from "@mui/material/Fade";

function AppbarDefault() {
  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElUser, setAnchorElUser] = React.useState(null);

  const [open, setOpen] = React.useState(false);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [anchorEl1, setAnchorEl1] = React.useState(null);
  const [open1, setOpen1] = React.useState(false);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
    setOpen((previousOpen) => !previousOpen);
  };

  const handleClick1 = (event) => {
    setAnchorEl1(event.currentTarget);
    setOpen1((previousOpen) => !previousOpen);
  };

  const canBeOpen = open && Boolean(anchorEl);
  const id = canBeOpen ? "transition-popper" : undefined;
  const id1 = canBeOpen ? "transition-popper1" : undefined;

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  return (
    <AppBar position="static" style={{ backgroundColor: "#282c34" }}>
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <a className="ikona" href="/">
            <MovieIcon fontSize="large" sx={{ display: { xs: "none", md: "flex" }, mr: 1 }}></MovieIcon>
          </a>

          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton fontSize="large" aria-label="account of current user" aria-controls="menu-appbar" aria-haspopup="true" onClick={handleOpenNavMenu} color="inherit">
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "bottom",
                horizontal: "left",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "left",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: "block", md: "none" },
              }}
            >
              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/movies">
                <Typography textAlign="center">Filmovi</Typography>
              </MenuItem>
              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/events">
                <Typography textAlign="center">Događaji</Typography>
              </MenuItem>
              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/aboutus">
                <Typography textAlign="center">O nama</Typography>
              </MenuItem>
            </Menu>
          </Box>

          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            <Button onClick={handleCloseNavMenu} component={Link} to="/movies" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Filmovi
            </Button>
            <Button onClick={handleCloseNavMenu} component={Link} to="/events" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Događaji
            </Button>
            <Button onClick={handleCloseNavMenu} component={Link} to="/aboutus" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              O nama
            </Button>
          </Box>
          <Box style={{ display: "flex" }}>
            <Button onClick={handleCloseNavMenu} component={Link} to="/login" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Prijava
            </Button>
            <Button onClick={handleCloseNavMenu} component={Link} to="/register" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Registracija
            </Button>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
}
export default AppbarDefault;
