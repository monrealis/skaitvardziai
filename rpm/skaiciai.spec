Summary: Foo to the Bar
Name: foobar
Version: 0.1
Release: 1
Group: Foo/Bar
License: FooBarPL
Source: %{expand:%%(pwd)}
BuildRoot: %{_topdir}/BUILD/%{name}-%{version}-%{release}

%description
%{summary}

%prep
rm -rf $RPM_BUILD_ROOT
mkdir -p $RPM_BUILD_ROOT/usr/bin
mkdir -p $RPM_BUILD_ROOT/etc
cd $RPM_BUILD_ROOT
cp %{SOURCEURL0}/foobar ./usr/bin/
cp %{SOURCEURL0}/foobar.conf ./etc/

%clean
rm -r -f "$RPM_BUILD_ROOT"

%files
%defattr(644,root,root)
%config(noreplace) %{_sysconfdir}/foobar.conf
%defattr(755,root,root)
%{_bindir}/foobar
