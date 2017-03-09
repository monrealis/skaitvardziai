%define _topdir %(echo $PWD)
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
mkdir -p $RPM_BUILD_ROOT/usr/lib
cd $RPM_BUILD_ROOT
cp %{_topdir}/skaiciai.sh ./usr/bin/skaiciai
cp %{_topdir}/../skaitvardziai-app/target/skaitvardziai-*-jar-with-dependencies.jar ./usr/lib/skaiciai.jar

%clean
rm -r -f "$RPM_BUILD_ROOT"

%files
%defattr(644,root,root)
%config(noreplace) %{_prefix}/lib/skaiciai.jar
%defattr(755,root,root)
%{_prefix}/bin/skaiciai
